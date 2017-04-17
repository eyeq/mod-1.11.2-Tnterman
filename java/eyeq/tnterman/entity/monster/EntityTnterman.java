package eyeq.tnterman.entity.monster;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class EntityTnterman extends EntityEnderman {
    public EntityTnterman(World world) {
        super(world);
        this.tasks.addTask(11, new EntityAITakeTnt(this));
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();

        List<EntityAITasks.EntityAITaskEntry> removeList = new ArrayList<>();
        int i = 0;
        for(EntityAITasks.EntityAITaskEntry taskEntry : this.tasks.taskEntries) {
            if(i == 6) {
                removeList.add(taskEntry);
            }
            i++;
        }
        for(EntityAITasks.EntityAITaskEntry taskEntry : removeList) {
            this.tasks.taskEntries.remove(taskEntry);
        }
        this.tasks.addTask(11, new EntityAITakeTnt(this));
    }

    public class EntityAITakeTnt extends EntityAIBase {
        private final EntityTnterman entity;

        public EntityAITakeTnt(EntityTnterman entity) {
            this.entity = entity;
        }

        @Override
        public boolean shouldExecute() {
            return entity.getRNG().nextInt(20) == 0;
        }

        @Override
        public void updateTask() {
            entity.setHeldBlockState(Blocks.TNT.getDefaultState());
        }
    }
}
