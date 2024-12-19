package net.minecraft.server;

import com.sathonay.npaper.utils.EntitySpecificSpawnPacket;

public class EntitySnowball extends EntityProjectile implements EntitySpecificSpawnPacket {
    public EntitySnowball(World var1) {
        super(var1);
    }

    public EntitySnowball(World var1, EntityLiving var2) {
        super(var1, var2);
    }

    public EntitySnowball(World var1, double var2, double var4, double var6) {
        super(var1, var2, var4, var6);
    }

    protected void a(MovingObjectPosition var1) {
        int var2;
        if (var1.entity != null) {
            var2 = 0;
            if (var1.entity instanceof EntityBlaze) {
                var2 = 3;
            }

            var1.entity.damageEntity(DamageSource.projectile(this, this.getShooter()), (float)var2);
        }

        for(var2 = 0; var2 < 8; ++var2) {
            this.world.addParticle("snowballpoof", this.locX, this.locY, this.locZ, 0.0, 0.0, 0.0);
        }

        if (!this.world.isStatic) {
            this.die();
        }

    }

    @Override
    public Packet createSpecificSpawnPacket() {
        return new PacketPlayOutSpawnEntity(this, 61);
    }
}
