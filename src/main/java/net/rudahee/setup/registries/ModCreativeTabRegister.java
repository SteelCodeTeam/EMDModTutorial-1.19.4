package net.rudahee.setup.registries;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.EmdTest;

@Mod.EventBusSubscriber(modid = EmdTest.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTabRegister {



    // Creamos un objeto del tipo CreativeModeTab, que contendrá la información de nuestra pestaña.
    public static CreativeModeTab EMD_TEST_TAB;



    // El objeto ResourceLocation es algo bastante común cuando programamos en Forge,
    // sería el equivalente a poner un String como este "emd_test:creative_tab",
    // que es el nombre interno de nuestra tab para Forge. Para simplificar esto, normalmente se crea un
    // ResourceLocation que vamos a usar cada vez que necesitemos crear una pestaña para nuestro mod.
    private static final ResourceLocation LOCATION = new ResourceLocation(EmdTest.MOD_ID, "creative_tab");



    // Al ser un evento, tenemos que poner @SuscribeEvent, lo veremos próximamente en el tutorial.
    @SubscribeEvent
    public static void registerTabs(CreativeModeTabEvent.Register event) {

        //Asignamos a nuestra variable el resultado del evento de registro, necesitamos pasar nuestro ResourceLocation y una funcion anónima (lambda)
        // que tomará el valor de un CreativeModeTab.Builder, en este caso solamente vamos a incluir un título a través de un Component
        // (que es el equivalente a un System.out.println de Minecraft) y le decimos que nos construya la pestaña a través del método build()
        EMD_TEST_TAB = event.registerCreativeModeTab(LOCATION, builder -> builder.title(Component.literal("Emd Test Mod")).build());

    }


}
