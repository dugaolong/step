package cn.dgl.www.step.utils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

import cn.dgl.www.step.bean.CityInfoModel;
import cn.dgl.www.step.bean.ProvinceInfoModel;

/**
 * 读取xml文件内容
 */
public class AddrXmlParser extends DefaultHandler {

    private List<ProvinceInfoModel> provinceList = new ArrayList<ProvinceInfoModel>();


    public java.util.List<ProvinceInfoModel> getDataList() {
        return provinceList;
    }

    @Override
    public void startDocument() throws SAXException {

    }

    ProvinceInfoModel provinceModel = new ProvinceInfoModel();
    CityInfoModel cityModel = new CityInfoModel();

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if (qName.equals("province")) {
            provinceModel = new ProvinceInfoModel();
            provinceModel.setName(attributes.getValue(0));
            provinceModel.setCityList(new ArrayList<CityInfoModel>());
        } else if (qName.equals("city")) {
            cityModel = new CityInfoModel();
            cityModel.setName(attributes.getValue(0));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equals("city")) {
            provinceModel.getCityList().add(cityModel);
        } else if (qName.equals("province")) {
            provinceList.add(provinceModel);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
    }
}