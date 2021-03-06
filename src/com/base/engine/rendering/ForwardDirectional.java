package com.base.engine.rendering;

import com.base.engine.components.BaseLight;
import com.base.engine.components.DirectionalLight;
import com.base.engine.core.Matrix4f;
import com.base.engine.core.Transform;

public class ForwardDirectional extends Shader {
    private static final ForwardDirectional instance = new ForwardDirectional();

    public static ForwardDirectional getInstance(){
        return instance;
    }

    public ForwardDirectional(){
        super();

        addVertexShaderFromFile("forward-directional.vs");
        addFragmentShaderFromFile("forward-directional.fs");

        compileShader();

        addUniform("model");
        addUniform("MVP");

        addUniform("specularIntensity");
        addUniform("specularPower");
        addUniform("eyePos");

        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
    }

    @Override
    public void updateUniforms(Transform transform, Material material){
        Matrix4f worldMatrix = transform.getTransformation();
        Matrix4f projectedMatrix = getRenderingEngine().getMainCamera().getViewProjection().mul(worldMatrix);
        material.getTexture().bind();

        setUniformf("specularIntensity", material.getSpecularIntensity());
        setUniformf("specularPower", material.getSpecularPower());
        setUniform("eyePos", getRenderingEngine().getMainCamera().getPosition());

        setUniform("model", worldMatrix);
        setUniform("MVP", projectedMatrix);

        setUniformDirectionalLight("directionalLight", (DirectionalLight)getRenderingEngine().getActiveLight());

    }


    public void setUniformBaseLight(String uniformName, BaseLight baseLight){
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
    }

    public void setUniformDirectionalLight(String uniformName, DirectionalLight directionalLight){
        setUniformBaseLight(uniformName + ".base", directionalLight);
        setUniform(uniformName + ".direction", directionalLight.getDirection());
    }
}
