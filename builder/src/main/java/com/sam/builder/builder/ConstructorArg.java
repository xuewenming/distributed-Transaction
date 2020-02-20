package com.sam.builder.builder;

/**
 * @author Mr.xuewenming
 * @title: ConstructorArg
 * @projectName distributed-Transaction
 * @description:
 * @date 2020/2/1810:00
 */
public class ConstructorArg {

    /*
     * @description:
     * 在下面的 ConstructorArg 类中，
     * 当 isRef 为 true 的时候，arg 表示 String 类型的 refBeanId，type 不需要设置；
     * 当 isRef 为 false 的时候，arg、type 都需要设置。
     * 请根据这个需求，完善 ConstructorArg 类。
     */

    private boolean isRef;
    private Class type;
    private Object arg;


    private ConstructorArg(Builder build) {
        this.type = build.type;
        this.arg = build.arg;
    }

    public boolean isRef() {
        return isRef;
    }

    public Class getType() {
        return type;
    }

    public Object getArg() {
        return arg;
    }

    /**
     * @description
     * 建造者模式创建对象
     */
    public static class Builder {
        private boolean isRef;
        private Class type;
        private Object arg;

        public ConstructorArg build() {
            if (isRef) {
                this.arg = (String) arg;
            }
            if (isRef == Boolean.FALSE) {
                if (this.arg == null || this.type == null) {
                    throw new IllegalArgumentException("arg or type is not null... ");
                }

                this.arg = arg;
                this.type = type;
            }
            return new ConstructorArg(this);
        }

        public Builder setRef(Boolean isRef) {
            this.isRef = isRef;
            return this;
        }

        public Builder setType(Class type) {
            this.type = type;
            return this;
        }

        public Builder setArg(Object arg) {
            this.arg = arg;
            return this;
        }
    }

}
