package eyeq.tnterman;

import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.common.registry.UEntityRegistry;
import eyeq.util.world.biome.BiomeUtils;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import eyeq.tnterman.entity.monster.EntityTnterman;
import eyeq.tnterman.client.renderer.entity.RenderTnterman;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;
import java.util.List;

import static eyeq.tnterman.Tnterman.MOD_ID;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
public class Tnterman {
    public static final String MOD_ID = "eyeq_tnterman";

    @Mod.Instance(MOD_ID)
    public static Tnterman instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        registerEntities();
        if(event.getSide().isServer()) {
            return;
        }
        registerEntityRenderings();
        createFiles();
    }

    public static void registerEntities() {
        EntityList.EntityEggInfo egg = EntityList.ENTITY_EGGS.get(new ResourceLocation("enderman"));
        UEntityRegistry.registerModEntity(resource, EntityTnterman.class, "Tnterman", 0, instance, egg.primaryColor, 0xDB441A);
        List<Biome> biomes = BiomeUtils.getSpawnBiomes(EntityEnderman.class, EnumCreatureType.MONSTER);
        EntityRegistry.addSpawn(EntityTnterman.class, 2, 1, 10, EnumCreatureType.MONSTER, biomes.toArray(new Biome[0]));
    }

    @SideOnly(Side.CLIENT)
    public static void registerEntityRenderings() {
        RenderingRegistry.registerEntityRenderingHandler(EntityTnterman.class, RenderTnterman::new);
    }

    public static void createFiles() {
        File project = new File("../1.11.2-Tnterman");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, EntityTnterman.class, "Tenterman");
        language.register(LanguageResourceManager.JA_JP, EntityTnterman.class, "テンターマン");

        ULanguageCreator.createLanguage(project, MOD_ID, language);
    }
}
