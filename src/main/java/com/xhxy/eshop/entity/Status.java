package com.xhxy.eshop.entity;

public enum Status {
	paid("已付款",1),		// 已付款
	pending("待处理",2),		// 待处理
	processing("处理中",3),	// 处理中
	received("已签收",4),	// 已签收
	Completed("已完成",5);	// 已完成
	
	
	private String name;
    private int index;
    
    //构造函数
    private Status(String name,int index){
        this.name=name;
        this.index=index;
    }
    
  //枚举方法
    public static String getStatusName(int index){
        for(Status status : Status.values()){
            if(status.getIndex()==index){
                return status.getName();
            }
        }
        return null;
    }


    //枚举方法
    public static int getStatusIndex(String name){
        for(Status status : Status.values()){
            if(status.getName()==name){
                return status.getIndex();
            }
        }
        return -1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
