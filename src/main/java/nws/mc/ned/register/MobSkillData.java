package nws.mc.ned.register;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.PrimitiveCodec;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.TagParser;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.event.entity.EntityInvulnerabilityCheckEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.*;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import nws.dev.core.array._Array3D;
import nws.dev.core.bytes._Byte;
import nws.dev.core.math._Math;
import nws.mc.ned.config.Configs;
import nws.mc.ned.config.general.GeneralConfig;
import nws.mc.ned.mob$skill.MobSkill;
import nws.mc.ned.mob$skill.MobSkillRegister;
import nws.mc.ned.mob$skill.MobSkills;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class MobSkillData {
    private static final Random random = new Random();
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final MobSkillData EMPTY = new MobSkillData(new _Array3D<>());
    public static final PrimitiveCodec<byte[]> BYTE = new PrimitiveCodec<>() {
        @Override
        public <T> DataResult<byte[]> read(final DynamicOps<T> ops, final T input) {
            return ops.getByteBuffer(input).map(ByteBuffer::array);
        }
        @Override
        public <T> T write(final DynamicOps<T> ops, final byte[] value) {
            /*
            ByteBuffer independentBuffer = ByteBuffer.allocate(value.length);
            independentBuffer.put(value);
            independentBuffer.flip();
             */
            return ops.createByteList(ByteBuffer.wrap(value));
        }
        @Override
        public String toString() {
            return "byteArray";
        }
    };
    public static final Codec<MobSkillData> CODEC = BYTE.xmap(MobSkillData::new, MobSkillData::getBytes);
    public static final Codec<MobSkillData> CODECC = Codec.withAlternative(CompoundTag.CODEC, TagParser.AS_CODEC).xmap(MobSkillData::new, MobSkillData::getCompoundTag);
    public static final StreamCodec<ByteBuf, MobSkillData> STREAM_CODEC = ByteBufCodecs.BYTE_ARRAY.map(MobSkillData::new, MobSkillData::getBytes);



    private final _Array3D<Integer, MobSkill,CompoundTag> skillData;
    private boolean needSync = true;

    private boolean firstSpawn = true;



    public MobSkillData(byte[] skillData) {
        try {
            this.skillData = (_Array3D<Integer,MobSkill,CompoundTag>) _Byte.deserialize(_Byte.decompress(skillData));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public MobSkillData(_Array3D<Integer,MobSkill,CompoundTag> skillData) {
        this.skillData = skillData;
    }

    public MobSkillData(CompoundTag compoundTag) {
        this.skillData = new _Array3D<>();
        handlePacket(compoundTag);
    }

    public boolean isFirstSpawn() {
        return firstSpawn;
    }

    public void setFirstSpawn(boolean firstSpawn) {
        this.firstSpawn = firstSpawn;
    }

    public boolean isNeedSync() {
        return needSync;
    }

    public void setNeedSync(boolean needSync) {
        this.needSync = needSync;
    }

    public byte[] getBytes() {
        try {
            return _Byte.compress(_Byte.serialize(this.skillData));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public MobSkillData copy() {
        return new MobSkillData(skillData);
    }
    public _Array3D<Integer, MobSkill, CompoundTag> getTagCopy(){
        return skillData.copy();
    }
    public _Array3D<Integer, MobSkill, CompoundTag> getUnsafe() {
        return this.skillData;
    }
    public MobSkillData update(Consumer<_Array3D<Integer, MobSkill, CompoundTag>> pUpdater) {
        _Array3D<Integer, MobSkill, CompoundTag> skillData = getTagCopy();
        pUpdater.accept(skillData);
        return new MobSkillData(skillData);
    }

    public MobSkill getSkill(Integer key) {
        return this.skillData.getA(key);
    }
    public CompoundTag getData(Integer key) {
        return this.skillData.getB(key);
    }

    public boolean isEmpty() {
        return !(this.skillData.getSize() > 0);
    }
    public void randomSkills() {
        skillData.clear();
        int s = random.nextInt(0, Configs.mobSkill().mobMaxSkill()) ;//GeneralConfig.INSTANCE.getRandomNumber();
        //System.out.println(s);
        for (int i = 0; i < s; i++) {
            MobSkill mobSkill = MobSkills.getRandomSkill();
            //System.out.println(mobSkill);
            if (mobSkill != null) giveNewSkill(MobSkills.getRandomSkill());
        }

    }
    public void giveNewSkill(MobSkill id){
        skillData.add(skillData.getSize(), id, new CompoundTag());
    }

    public void spawnEvent(FinalizeSpawnEvent spawnEvent) {
        skillData.forEach((data)-> data.a.mobSpawn(spawnEvent,data.b));
    }
    public void entityJoinLevel(EntityJoinLevelEvent event){
        skillData.forEach((data)-> data.a.entityJoinLevel(event,data.b));
    }
    public void entityInvulnerabilityCheck(EntityInvulnerabilityCheckEvent event){
        skillData.forEach((data)-> data.a.entityInvulnerabilityCheck(event,data.b));
    }
    public void livingIncomingDamage(LivingIncomingDamageEvent event){
        skillData.forEach((data)-> data.a.livingIncomingDamage(event,data.b));
    }
    public void livingDamagePre(LivingDamageEvent.Pre event){
        skillData.forEach((data)-> data.a.livingDamagePre(event,data.b));
    }
    public void livingDamagePost(LivingDamageEvent.Post event){
        skillData.forEach((data)-> data.a.livingDamagePost(event,data.b));
    }
    public void livingDeath(LivingDeathEvent event){
        skillData.forEach((data)-> data.a.livingDeath(event,data.b));
    }
    public void entityTickPre(EntityTickEvent.Pre event){
        skillData.forEach((data)-> data.a.entityTickPre(event,data.b));
    }

    public boolean hasSkill(MobSkill skill) {
        return skillData.getAs().contains(skill);
    }
    public List<MobSkill> getAllSkill() {
        return skillData.getAs();
    }

    public CompoundTag getCompoundTag(){
        CompoundTag nbt = new CompoundTag();
        ListTag tags = new ListTag();
        skillData.forEach((data)->{
            CompoundTag tag = new CompoundTag();
            tag.putInt("slot",data.key);
            tag.putString("id", MobSkillRegister.REGISTRY.getKey(data.a).toString());
            tag.put("data", data.b);
            tags.add(tag);
        });
        nbt.put("mob_skill.ned.list",tags);
        nbt.putBoolean("mob_skill.ned.spawned",isFirstSpawn());
        return nbt;
    }
    public void handlePacket(@NotNull CompoundTag dat) {
        skillData.clear();
        ListTag tags = dat.getList("mob_skill.ned.list", Tag.TAG_COMPOUND);
        for (int i = 0; i < tags.size(); i++) {
            CompoundTag tag = tags.getCompound(i);
            skillData.add(
                    tag.getInt("slot"),
                    MobSkills.getMobSkill(ResourceLocation.tryParse(tag.getString("id"))),
                    tag.getCompound("data"));
        }
        setFirstSpawn(dat.getBoolean("mob_skill.ned.spawned"));
    }
}
