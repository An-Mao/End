package nws.mc.ned.mob$enchant.skill.a;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import nws.mc.cores.helper.entity.EntityHelper;
import nws.mc.ned.NekoEnd;
import nws.mc.ned.config.Configs;
import nws.mc.ned.mob$enchant.skill.MobSkill;
import nws.mc.ned.mob$enchant.skill.MobSkillHelper;
import nws.mc.ned.mob$enchant.skill.MobSkillRegister;
import nws.mc.ned.register.data.DataRegister;
import nws.mc.ned.register.data.MobSkillData;

import java.util.List;

public class PullMobSkill extends MobSkill {
    private double moveSpeed = 0.2D;
    private double checkDistance = 20;
    public PullMobSkill(String id) {
        super(id);
    }

    @Override
    public boolean canAdd(List<MobSkill> skills) {
        boolean[] can = {true};
        skills.forEach(mobSkill -> {
            if (mobSkill.getId().equals("chase_away")) can[0] = false;
        });
        return can[0];
    }

    @Override
    public void loadConfig(CompoundTag dat) {
        moveSpeed = dat.getDouble("moveSpeed");
        checkDistance = dat.getDouble("checkDistance");
    }
    @Override
    public CompoundTag getDefaultConfig() {
        CompoundTag dat = super.getDefaultConfig();
        dat.putDouble("moveSpeed",moveSpeed);
        dat.putDouble("checkDistance",checkDistance);
        return dat;
    }
    public double getMoveSpeed() {
        return moveSpeed;
    }
    public double getCheckDistance() {
        return checkDistance;
    }
    @EventBusSubscriber(modid = NekoEnd.MOD_ID,value = Dist.CLIENT)
    public static class TentacleMobSkillClientEvent {
        private static int updateTimer = 0;
        private static final int UPDATE_INTERVAL = 5;
        private static Vec3 targetMotion = Vec3.ZERO; // 目标运动向量
        /*
        public static void onClientTick(LevelTickEvent.Pre event){
            if (event.getLevel().isClientSide() && Configs.mobSkill().enable() && MobSkillHelper.isEnable(event.getLevel(),"tentacle")) {
                LocalPlayer player = Minecraft.getInstance().player;
                if (player != null) {
                    TentacleMobSkill tentacleMobSkill = (TentacleMobSkill) event.getLevel().registryAccess().holderOrThrow(MobSkillRegister.Pull.getKey()).value();
                    List<? extends LivingEntity> entities = EntityHelper.getLevel(player).getEntities(EntityTypeTest.forClass(LivingEntity.class), player.getBoundingBox().inflate(tentacleMobSkill.getCheckDistance()), Entity::isAlive);
                    final boolean[] b = {false};
                    for (LivingEntity livingEntity : entities) {
                        MobSkillData skillData = livingEntity.getData(DataRegister.MOB_SKILLS.get());
                        if (skillData.hasSkill(MobSkillRegister.Pull.get())) {
                            double directionX = livingEntity.getX() - player.getX();
                            double directionY = livingEntity.getY() - player.getY();
                            double directionZ = livingEntity.getZ() - player.getZ();
                            double distance = Math.sqrt(directionX * directionX + directionY * directionY + directionZ * directionZ);
                            if (distance > 0) {
                                distance *= tentacleMobSkill.getMoveSpeed();
                                double motionX = directionX / distance;
                                double motionY = directionY / distance;
                                double motionZ = directionZ / distance;
                                player.lerpMotion(motionX, motionY, motionZ);
                                break;
                            }

                        }
                    }
                }
            }
        }

         */
        /*
        @SubscribeEvent
        public static void onClientTick(LevelTickEvent.Pre event) {
            if (event.getLevel().isClientSide() && Configs.mobSkill().enable() && MobSkillHelper.isEnable(event.getLevel(), "tentacle")) {
                LocalPlayer player = Minecraft.getInstance().player;
                if (player != null) {
                    TentacleMobSkill tentacleMobSkill = (TentacleMobSkill) event.getLevel().registryAccess().holderOrThrow(MobSkillRegister.Pull.getKey()).value();
                    List<? extends LivingEntity> entities = EntityHelper.getLevel(player).getEntities(EntityTypeTest.forClass(LivingEntity.class), player.getBoundingBox().inflate(tentacleMobSkill.getCheckDistance()), Entity::isAlive);

                    if (updateTimer >= UPDATE_INTERVAL) {
                        updateTimer = 0;
                        LivingEntity closestEntity = null;
                        double closestDistanceSq = Double.MAX_VALUE;

                        for (LivingEntity livingEntity : entities) {
                            MobSkillData skillData = livingEntity.getData(DataRegister.MOB_SKILLS.get());
                            if (skillData.hasSkill(MobSkillRegister.Pull.get())) {
                                double distanceSq = player.distanceToSqr(livingEntity);
                                if (distanceSq < closestDistanceSq) {
                                    closestDistanceSq = distanceSq;
                                    closestEntity = livingEntity;
                                }
                            }
                        }

                        if (closestEntity != null) {
                            double directionX = closestEntity.getX() - player.getX();
                            double directionY = closestEntity.getY() - player.getY();
                            double directionZ = closestEntity.getZ() - player.getZ();
                            double distance = Math.sqrt(directionX * directionX + directionY * directionY + directionZ * directionZ);

                            if (distance > 0) {
                                double speed = tentacleMobSkill.getMoveSpeed();
                                double motionX = directionX / distance * speed;
                                double motionY = directionY / distance * speed;
                                double motionZ = directionZ / distance * speed;
                                Vec3 currentMotion = player.getDeltaMovement();
                                player.setDeltaMovement(motionX, currentMotion.y + motionY, motionZ);
                            }
                        }
                    }

                    updateTimer++;
                }
            }
        }

         */
        @SubscribeEvent
        public static void onPlayerTick(PlayerTickEvent.Pre event) {
            if (event.getEntity() instanceof LocalPlayer player) {
                if (player.level().isClientSide() && Configs.mobSkill().enable() && MobSkillHelper.isEnable(player.level(), "pull")) {

                    PullMobSkill pullMobSkill = (PullMobSkill) player.level().registryAccess().holderOrThrow(MobSkillRegister.Pull.getKey()).value();
                    List<? extends LivingEntity> entities = EntityHelper.getLevel(player).getEntities(EntityTypeTest.forClass(LivingEntity.class), player.getBoundingBox().inflate(pullMobSkill.getCheckDistance()), Entity::isAlive);

                    if (updateTimer >= UPDATE_INTERVAL) {
                        updateTimer = 0;

                        LivingEntity closestEntity = null;
                        double closestDistanceSq = Double.MAX_VALUE;

                        for (LivingEntity livingEntity : entities) {
                            MobSkillData skillData = livingEntity.getData(DataRegister.MOB_SKILLS.get());
                            if (skillData.hasSkill(MobSkillRegister.Pull.get())) {
                                double distanceSq = player.distanceToSqr(livingEntity);
                                if (distanceSq < closestDistanceSq) {
                                    closestDistanceSq = distanceSq;
                                    closestEntity = livingEntity;
                                }
                            }
                        }

                        if (closestEntity != null) {
                            double directionX = closestEntity.getX() - player.getX();
                            double directionY = closestEntity.getY() - player.getY();
                            double directionZ = closestEntity.getZ() - player.getZ();
                            double distance = Math.sqrt(directionX * directionX + directionY * directionY + directionZ * directionZ);

                            if (distance > 0) {
                                double speed = pullMobSkill.getMoveSpeed();
                                double motionX = directionX / distance * speed;
                                double motionY = directionY / distance * speed;
                                double motionZ = directionZ / distance * speed;
                                targetMotion = new Vec3(motionX, motionY, motionZ); // 更新目标运动向量
                            } else {
                                targetMotion = Vec3.ZERO;
                            }
                        }else {
                            targetMotion = Vec3.ZERO; // 如果没有目标生物，目标运动向量为 0
                        }
                    }
                    // 使用 lerpMotion 平滑过渡
                    Vec3 currentMotion = player.getDeltaMovement();
                    double lerpFactor = 0.2; // 平滑过渡的程度，值越大过渡越快
                    double lerpedX = currentMotion.x + (targetMotion.x - currentMotion.x) * lerpFactor;
                    double lerpedY = currentMotion.y + (targetMotion.y - currentMotion.y) * lerpFactor;
                    double lerpedZ = currentMotion.z + (targetMotion.z - currentMotion.z) * lerpFactor;
                    player.setDeltaMovement(lerpedX, lerpedY, lerpedZ);
                    updateTimer++;

                }
            }
        }
    }
}
