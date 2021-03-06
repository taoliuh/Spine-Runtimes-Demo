package me.lancer.spineruntimesdemo.model;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.esotericsoftware.spine.AnimationState;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SkeletonJson;
import com.esotericsoftware.spine.SkeletonRenderer;
import com.esotericsoftware.spine.SkeletonRendererDebug;
import com.esotericsoftware.spine.utils.TwoColorPolygonBatch;

public class Raptor extends ApplicationAdapter {

    OrthographicCamera camera;
    TwoColorPolygonBatch batch;
    SkeletonRenderer renderer;
    SkeletonRendererDebug debugRenderer;
    TextureAtlas atlas;
    Skeleton skeleton;
    AnimationState state;
    SkeletonJson json;

    public void create() {
        camera = new OrthographicCamera();
        batch = new TwoColorPolygonBatch();
        renderer = new SkeletonRenderer();
        renderer.setPremultipliedAlpha(false); // PMA results in correct blending without outlines.
        debugRenderer = new SkeletonRendererDebug();
        debugRenderer.setBoundingBoxes(false);
        debugRenderer.setRegionAttachments(false);
        atlas = new TextureAtlas(Gdx.files.internal("raptor/raptor-pma.atlas"));
        json = new SkeletonJson(atlas); // This loads skeleton JSON data, which is stateless.
        json.setScale(0.5f); // Load the skeleton at 60% the size it was in Spine.
        SkeletonData skeletonData = json.readSkeletonData(Gdx.files.internal("raptor/raptor-pro.json"));

        skeleton = new Skeleton(skeletonData); // Skeleton holds skeleton state (bone positions, slot attachments, etc).
        skeleton.setPosition(450, 100);

        AnimationStateData stateData = new AnimationStateData(skeletonData); // Defines mixing (crossfading) between animations.
        stateData.setMix("gun-grab", "walk", 0.2f);
        stateData.setMix("walk", "gun-grab", 0.2f);

        state = new AnimationState(stateData); // Holds the animation state for a skeleton (current animation, time, etc).
        state.setTimeScale(1.0f); // Slow all animations down to 50% speed.

        // Queue animations on track 0.
        state.setAnimation(0, "gun-grab", true);

        state.addAnimation(0, "walk", true, 0); // Run after the jump.

        state.addAnimation(0, "roar", true, 5); // Run after the jump.
    }

    public void render() {
        state.update(Gdx.graphics.getDeltaTime()); // Update the animation time.

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Gdx.gl.glClearColor(0, 0, 0, 0);

        state.apply(skeleton); // Poses skeleton using current animations. This sets the bones' local SRT.
        skeleton.updateWorldTransform(); // Uses the bones' local SRT to compute their world SRT.

        // Configure the camera, SpriteBatch, and SkeletonRendererDebug.
        camera.update();
        batch.getProjectionMatrix().set(camera.combined);
        debugRenderer.getShapeRenderer().setProjectionMatrix(camera.combined);

        batch.begin();
        renderer.draw(batch, skeleton); // Draw the skeleton images.
        batch.end();

//        debugRenderer.draw(skeleton); // Draw debug lines.
    }

    public void resize(int width, int height) {
        camera.setToOrtho(false); // Update camera with new size.
    }

    public void dispose() {
        atlas.dispose();
    }

    public void setAnimate() {
        setAnimate("walk");
        setAnimate("roar");
    }

    public void setAnimate(String animate) {
        state.addAnimation(0, animate, true, 0);
    }

    public void zoomBig() {
        camera.zoom = 0.5f;
    }

    public void zoomSmall() {
        camera.zoom = 1f;
    }
}
