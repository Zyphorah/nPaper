package net.minecraft.server;

import com.sathonay.npaper.utils.EntitySpecificSpawnPacket;

public class EntityEnderSignal extends Entity implements EntitySpecificSpawnPacket {
    private double a;
    private double b;
    private double c;
    private int d;
    private boolean e;

    public EntityEnderSignal(World var1) {
        super(var1);
        this.a(0.25F, 0.25F);
    }

    protected void c() {
    }

    public EntityEnderSignal(World var1, double var2, double var4, double var6) {
        super(var1);
        this.d = 0;
        this.a(0.25F, 0.25F);
        this.setPosition(var2, var4, var6);
        this.height = 0.0F;
    }

    public void a(double var1, int var3, double var4) {
        double var6 = var1 - this.locX;
        double var8 = var4 - this.locZ;
        float var10 = MathHelper.sqrt(var6 * var6 + var8 * var8);
        if (var10 > 12.0F) {
            this.a = this.locX + var6 / (double)var10 * 12.0;
            this.c = this.locZ + var8 / (double)var10 * 12.0;
            this.b = this.locY + 8.0;
        } else {
            this.a = var1;
            this.b = (double)var3;
            this.c = var4;
        }

        this.d = 0;
        this.e = this.random.nextInt(5) > 0;
    }

    public void h() {
        this.S = this.locX;
        this.T = this.locY;
        this.U = this.locZ;
        super.h();
        this.locX += this.motX;
        this.locY += this.motY;
        this.locZ += this.motZ;
        float var1 = MathHelper.sqrt(this.motX * this.motX + this.motZ * this.motZ);
        this.yaw = (float)(Math.atan2(this.motX, this.motZ) * 180.0 / 3.1415927410125732);

        for(this.pitch = (float)(Math.atan2(this.motY, (double)var1) * 180.0 / 3.1415927410125732); this.pitch - this.lastPitch < -180.0F; this.lastPitch -= 360.0F) {
        }

        while(this.pitch - this.lastPitch >= 180.0F) {
            this.lastPitch += 360.0F;
        }

        while(this.yaw - this.lastYaw < -180.0F) {
            this.lastYaw -= 360.0F;
        }

        while(this.yaw - this.lastYaw >= 180.0F) {
            this.lastYaw += 360.0F;
        }

        this.pitch = this.lastPitch + (this.pitch - this.lastPitch) * 0.2F;
        this.yaw = this.lastYaw + (this.yaw - this.lastYaw) * 0.2F;
        if (!this.world.isStatic) {
            double var2 = this.a - this.locX;
            double var4 = this.c - this.locZ;
            float var6 = (float)Math.sqrt(var2 * var2 + var4 * var4);
            float var7 = (float)Math.atan2(var4, var2);
            double var8 = (double)var1 + (double)(var6 - var1) * 0.0025;
            if (var6 < 1.0F) {
                var8 *= 0.8;
                this.motY *= 0.8;
            }

            this.motX = Math.cos((double)var7) * var8;
            this.motZ = Math.sin((double)var7) * var8;
            if (this.locY < this.b) {
                this.motY += (1.0 - this.motY) * 0.014999999664723873;
            } else {
                this.motY += (-1.0 - this.motY) * 0.014999999664723873;
            }
        }

        float var10 = 0.25F;
        if (this.M()) {
            for(int var11 = 0; var11 < 4; ++var11) {
                this.world.addParticle("bubble", this.locX - this.motX * (double)var10, this.locY - this.motY * (double)var10, this.locZ - this.motZ * (double)var10, this.motX, this.motY, this.motZ);
            }
        } else {
            this.world.addParticle("portal", this.locX - this.motX * (double)var10 + this.random.nextDouble() * 0.6 - 0.3, this.locY - this.motY * (double)var10 - 0.5, this.locZ - this.motZ * (double)var10 + this.random.nextDouble() * 0.6 - 0.3, this.motX, this.motY, this.motZ);
        }

        if (!this.world.isStatic) {
            this.setPosition(this.locX, this.locY, this.locZ);
            ++this.d;
            if (this.d > 80 && !this.world.isStatic) {
                this.die();
                if (this.e) {
                    this.world.addEntity(new EntityItem(this.world, this.locX, this.locY, this.locZ, new ItemStack(Items.EYE_OF_ENDER)));
                } else {
                    this.world.triggerEffect(2003, (int)Math.round(this.locX), (int)Math.round(this.locY), (int)Math.round(this.locZ), 0);
                }
            }
        }

    }

    public void b(NBTTagCompound var1) {
    }

    public void a(NBTTagCompound var1) {
    }

    public float d(float var1) {
        return 1.0F;
    }

    public boolean av() {
        return false;
    }

    @Override
    public Packet createSpecificSpawnPacket() {
        return new PacketPlayOutSpawnEntity(entity, 72);
    }
}
