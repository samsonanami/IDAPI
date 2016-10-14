package com.fintech.orion.dataabstraction.entities.orion;
// Generated Oct 14, 2016 9:57:19 AM by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProcessConfigId generated by hbm2java
 */
@Embeddable
public class ProcessConfigId  implements java.io.Serializable {


     private int processType;
     private String key;

    public ProcessConfigId() {
    }

    public ProcessConfigId(int processType, String key) {
       this.processType = processType;
       this.key = key;
    }
   


    @Column(name="PROCESS_TYPE", nullable=false)
    public int getProcessType() {
        return this.processType;
    }
    
    public void setProcessType(int processType) {
        this.processType = processType;
    }


    @Column(name="KEY", nullable=false, length=45)
    public String getKey() {
        return this.key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ProcessConfigId) ) return false;
		 ProcessConfigId castOther = ( ProcessConfigId ) other; 
         
		 return (this.getProcessType()==castOther.getProcessType())
 && ( (this.getKey()==castOther.getKey()) || ( this.getKey()!=null && castOther.getKey()!=null && this.getKey().equals(castOther.getKey()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getProcessType();
         result = 37 * result + ( getKey() == null ? 0 : this.getKey().hashCode() );
         return result;
   }   


}


