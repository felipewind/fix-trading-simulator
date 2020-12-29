package com.helesto.dto;

public class NewOrderSingleResponseDto implements java.io.Serializable {

        private static final long serialVersionUID = 1L;

       private boolean sendToTarget;

       public boolean isSendToTarget() {
               return sendToTarget;
       }

       public void setSendToTarget(boolean sendToTarget) {
               this.sendToTarget = sendToTarget;
       }      

}