package eyeq.tnterman.client.renderer.entity;

import eyeq.util.client.renderer.EntityRenderResourceLocation;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;

import static eyeq.tnterman.Tnterman.MOD_ID;

public class RenderTnterman extends RenderEnderman {
    protected static final ResourceLocation textures = new EntityRenderResourceLocation(MOD_ID, "tnterman");

    public RenderTnterman(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityEnderman entity) {
        return textures;
    }
}
