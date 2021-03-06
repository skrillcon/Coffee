package com.base.game;

import com.base.engine.components.*;
import com.base.engine.core.*;
import com.base.engine.rendering.*;

public class TestGame extends Game {

    //private Camera camera;

    public TestGame(){

    }

    public void init(){
        //camera = new Camera(new Vector3f(0,0,0), new Vector3f(0,0,1), new Vector3f(0,1,0));

        float fieldDepth = 10.0f;
        float fieldWidth = 10.0f;
        Vertex[] vertices = new Vertex[] {  new Vertex(new Vector3f(-fieldWidth, 0.0f, -fieldDepth), new Vector2f(0.0f, 0.0f)),
                new Vertex(new Vector3f(-fieldWidth, 0.0f, fieldDepth * 3), new Vector2f(0.0f, 1.0f)),
                new Vertex(new Vector3f(fieldWidth * 3, 0.0f, -fieldDepth), new Vector2f(1.0f, 0.0f)),
                new Vertex(new Vector3f(fieldWidth * 3, 0.0f, fieldDepth * 3), new Vector2f(1.0f, 1.0f))
        };

        int indices[] = {   0, 1, 2,
                2, 1, 3};
        Mesh mesh = new Mesh(vertices, indices, true);
        Material material = new Material(new Texture("Marble.jpg"), new Vector3f(1.0f, 1.0f, 1.0f), 1, 16);

        MeshRenderer meshRenderer = new MeshRenderer(mesh, material);

        GameObject planeObject = new GameObject();
        planeObject.addComponent(meshRenderer);
        planeObject.getTransform().getPosition().set(0, -1, 5);

        GameObject directionalLightObject = new GameObject();
        DirectionalLight directionalLight = new DirectionalLight(new Vector3f(1,0,0), 0.2f,
                new Vector3f(1,-1,1));

        directionalLightObject.addComponent(directionalLight);

        GameObject pointLightObject = new GameObject();
        PointLight pointLight = new PointLight(new Vector3f(0, 1, 0), 0.6f,
                        new Vector3f(0,0,1));

        pointLightObject.addComponent(pointLight);

        GameObject spotLightObject = new GameObject();
        SpotLight spotLight = new SpotLight(new Vector3f(0, 0, 1), 1f, new Vector3f(0,0,1), 0.6f);
        spotLightObject.addComponent(spotLight);

        spotLightObject.getTransform().getPosition().set(5f, 0f, 5f);
        spotLightObject.getTransform().setRotation(new Quaternion().initRotation(new Vector3f(0,1,0), (float)Math.toRadians(-90.0f)));
        getRootObject().addChild(planeObject);
        getRootObject().addChild(directionalLightObject);
        getRootObject().addChild(pointLightObject);
        getRootObject().addChild(spotLightObject);
    }

}
