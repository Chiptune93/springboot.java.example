package com.springcloud.webserver.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DK
 * @since 2022.06.07
 * @apiNote XML 데이터를 만들기 위한 객체, JAXB 사용
 */
@Getter
@XmlRootElement(name = "response")
public class exampleXmlData {

    /**
     * @apiNote
     *          XML 데이터를 생성하기 위한 객체, JAXB 사용.
     *          내부에 정의된 데이터를 set 하기 위해 lombok이 아닌
     *          커스텀 setter메소드가 정의되었다.
     *          client의 받기위한 객체와 비교해보면 좋다.
     * 
     */

    @XmlElement
    private Header header;
    @XmlElement
    private Body body;

    @Getter
    @Setter
    @XmlRootElement(name = "header")
    public static class Header {

        String resultCode;
        String resultMsg;

    }

    public void setHeader(String resultCode, String resultMsg) {
        if (this.header == null)
            this.header = new Header();
        this.header.setResultCode(resultCode);
        this.header.setResultMsg(resultMsg);
    }

    public void setBody(Integer numOfRows, Integer pageNo, Integer totalCount) {
        if (this.body == null)
            this.body = new Body();
        this.body.setNumOfRows(numOfRows);
        this.body.setPageNo(pageNo);
        this.body.setTotalCount(totalCount);
    }

    public void setItemList() {
        this.body.setItems(this.body.itemList);
    }

    public void setItem(String dataKind, String dataName) {
        item i = new item();
        i.setDataKind(dataKind);
        i.setDataName(dataName);
        this.body.addItem(i);
    }

    @Getter
    @XmlRootElement(name = "body")
    public static class Body {

        @XmlElementWrapper(name = "items")
        @XmlElement(name = "item")
        private List<item> itemList = new ArrayList<item>();

        public void addItem(item i) {
            if (this.itemList == null)
                this.itemList = new ArrayList<item>();
            else
                this.itemList.add(i);
        }

        public void setItems(List<item> itemList) {
            this.itemList = itemList;
        }

        private Integer numOfRows;
        private Integer pageNo;
        private Integer totalCount;

        public void setNumOfRows(Integer numOfRows) {
            this.numOfRows = numOfRows;
        }

        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }

        public void setTotalCount(Integer totalCount) {
            this.totalCount = totalCount;
        }

    }

    @Getter
    @Setter
    @XmlRootElement(name = "item")
    public static class item {
        private String dataKind;
        private String dataName;
    }
}
