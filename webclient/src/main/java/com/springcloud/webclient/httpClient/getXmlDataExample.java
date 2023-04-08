package com.springcloud.webclient.httpClient;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * @author DK
 * @serial 2022.06.07
 * @apiNote XML데이터를 받기 위한 데이터 객체 JAXB 사용.
 */
@Getter
@XmlRootElement(name = "response")
public class getXmlDataExample {

    /* 아래 데이터를 받기 위한 구조이다. */
    /**
     * @XmlRootElement : 루트 엘리먼트, 제일 바깥에 감싸는 태그를 지정하기 위해 사용.
     * @XmlElement : 따로 정의된 형식을 받는 경우에 엘리먼트 지정을 위해 사용. 내부에 다른 엘리먼트가 정의 된다.
     * @XmlElementWrapper : 리스트 형식의 엘리먼트의 경우 최상단에 감싸는 엘리먼트를 정의하기 위해 사용.
     * @apiNote
     *          엘리먼트 내부에 선언된 값들, 아래와 같은 형식인 경우에는 setter 메소드를 통해 해당 값을 가져오게 된다.
     *          만약, <data id="1"> 과 같이 엘리먼트 내 속성으로 지정된 경우에는 @XmlAttribute 라는 어노테이션을
     *          통해 가져온다.
     *          아래 케이스의 경우에는 lombok을 사용하여 setter 메소드를 정의했지만, 엘리먼트 내 리스트 형식과 혼재되는
     *          경우에는 setter 메소드를 다르게 정의해주어야만 내부 값들을 가져올 수 있다.
     * 
     *          <response>
     *          <header>
     *          <resultCode>200</resultCode>
     *          <resultMsg>success</resultMsg>
     *          </header>
     *          <body>
     *          <items>
     *          <item>
     *          <dataKind>1</dataKind>
     *          <dataName>테스트1</dataName>
     *          </item>
     *          <item>
     *          <dataKind>2</dataKind>
     *          <dataName>테스트2</dataName>
     *          </item>
     *          <item>
     *          <dataKind>3</dataKind>
     *          <dataName>테스트3</dataName>
     *          </item>
     *          <item>
     *          <dataKind>4</dataKind>
     *          <dataName>테스트4</dataName>
     *          </item>
     *          </items>
     *          <numOfRows>1</numOfRows>
     *          <pageNo>1</pageNo>
     *          <totalCount>4</totalCount>
     *          </body>
     *          </response>
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

    @Getter
    @XmlRootElement(name = "body")
    public static class Body {

        @XmlElementWrapper(name = "items")
        @XmlElement(name = "item")
        private List<item> itemList = new ArrayList<>();

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
