package com.weaveown;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author wangwei
 * @date 2021/5/31
 */
@Data
public class ObjTest {
    private String name;

    @Data
    class Obj {
        private String obj;

        public void sout() {
            objSout();
        }
    }

    public void objSout() {
        System.out.println(this);
    }

    @Data
    class SubObj extends Obj {
        private String sub;
    }

    void sub(Obj obj) {
        final Obj obj1 = new Obj();
    }

    static void obj(Obj obj) {
        System.out.println(JSON.toJSONString(obj));
    }

    public static void main(String[] args) {

    }
}
