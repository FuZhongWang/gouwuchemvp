package com.example.yuekaomoni.model.bean;

import java.util.List;

public class JsonDataBean {


    private List<ContentBean> content;

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * id : 0
         * address : NIKE官方旗舰店
         * isSelected : false
         * goodDetail : [{"id":"00","pic":"11","count":"1","name":"BB霜","price":"99","isEdit":false,"isSelected":false},{"id":"01","pic":"11","count":"1","name":"男士洁面乳","price":"66","isEdit":false,"isSelected":false},{"id":"02","pic":"11","count":"1","name":"眼线笔","price":"16","isEdit":false,"isSelected":false}]
         */

        private String id;
        private String address;
        private boolean isSelected;
        private List<GoodDetailBean> goodDetail;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public boolean isIsSelected() {
            return isSelected;
        }

        public void setIsSelected(boolean isSelected) {
            this.isSelected = isSelected;
        }

        public List<GoodDetailBean> getGoodDetail() {
            return goodDetail;
        }

        public void setGoodDetail(List<GoodDetailBean> goodDetail) {
            this.goodDetail = goodDetail;
        }

        public static class GoodDetailBean {
            /**
             * id : 00
             * pic : 11
             * count : 1
             * name : BB霜
             * price : 99
             * isEdit : false
             * isSelected : false
             */

            private String id;
            private String pic;
            private String count;
            private String name;
            private String price;
            private boolean isEdit;
            private boolean isSelected;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public boolean isIsEdit() {
                return isEdit;
            }

            public void setIsEdit(boolean isEdit) {
                this.isEdit = isEdit;
            }

            public boolean isIsSelected() {
                return isSelected;
            }

            public void setIsSelected(boolean isSelected) {
                this.isSelected = isSelected;
            }
        }
    }
}
