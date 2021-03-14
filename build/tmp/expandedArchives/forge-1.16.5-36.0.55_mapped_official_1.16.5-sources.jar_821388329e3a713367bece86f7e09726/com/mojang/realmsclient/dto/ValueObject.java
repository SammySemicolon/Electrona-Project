package com.mojang.realmsclient.dto;

import com.google.gson.annotations.SerializedName;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class ValueObject {
   public String toString() {
      StringBuilder stringbuilder = new StringBuilder("{");

      for(Field field : this.getClass().getFields()) {
         if (!isStatic(field)) {
            try {
               stringbuilder.append(getName(field)).append("=").append(field.get(this)).append(" ");
            } catch (IllegalAccessException illegalaccessexception) {
            }
         }
      }

      stringbuilder.deleteCharAt(stringbuilder.length() - 1);
      stringbuilder.append('}');
      return stringbuilder.toString();
   }

   private static String getName(Field p_237702_0_) {
      SerializedName serializedname = p_237702_0_.getAnnotation(SerializedName.class);
      return serializedname != null ? serializedname.value() : p_237702_0_.getName();
   }

   private static boolean isStatic(Field p_230801_0_) {
      return Modifier.isStatic(p_230801_0_.getModifiers());
   }
}
