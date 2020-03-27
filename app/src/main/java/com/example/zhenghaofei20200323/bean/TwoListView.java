package com.example.zhenghaofei20200323.bean;

import java.util.List;

public class TwoListView {



    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1001002
         * name : 女装
         * secondCategoryVo : [{"id":"1001002001","name":"外套"},{"id":"1001002002","name":"裙装"},{"id":"1001002003","name":"打底毛衣"},{"id":"1001002004","name":"卫衣"},{"id":"1001002005","name":"裤装"}]
         */

        private String id;
        private String name;
        private List<SecondCategoryVoBean> secondCategoryVo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<SecondCategoryVoBean> getSecondCategoryVo() {
            return secondCategoryVo;
        }

        public void setSecondCategoryVo(List<SecondCategoryVoBean> secondCategoryVo) {
            this.secondCategoryVo = secondCategoryVo;
        }

        public static class SecondCategoryVoBean {
            /**
             * id : 1001002001
             * name : 外套
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
