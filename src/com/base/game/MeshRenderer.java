package com.base.game;

import com.base.engine.core.Component;
import com.base.engine.core.Transform;
import com.base.engine.rendering.BasicShader;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.Shader;

public class MeshRenderer implements Component {

    private Mesh mesh;
    private Material material;

    public MeshRenderer(Mesh mesh, Material material){
        this.mesh = mesh;
        this.material = material;
    }

    @Override
    public void input(Transform transform) {

    }

    @Override
    public void update(Transform transform) {

    }

    @Override
    public void render(Transform transform) {
        Shader shader = BasicShader.getInstance();
        shader.bind();
        shader.updateUniforms(transform.getTransformation(), transform.getProjectedTransformation(), material);
        mesh.draw();
        shader.unbind();
    }
}
