package com.iShamrock.iMuseum.util;

import android.util.Xml;
import com.iShamrock.iMuseum.data.DataItem;
import com.iShamrock.iMuseum.data.ShowroomItem;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mayezhou on 16/2/6.
 */
public class XmlParser {
    //do not use namespaces
    private static final String namespace = null;

    public List<ShowroomItem> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            //starts the parsing process
            parser.nextTag();
            return readData(parser);//extracts and processes the data the app is interested in
        } finally {
            in.close();
        }
    }

    private List<ShowroomItem> readData(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<ShowroomItem> exhibitionHalls = new ArrayList<ShowroomItem>();
        String name = null;
        String englishName = null;
        String floor = null;
        parser.require(XmlPullParser.START_TAG, namespace, "resources");
        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tag = parser.getName();
            //start by looking for the exhibitionHall tag
            if (tag.equals("exhibitionHall")) {
                exhibitionHalls.add(readExhibitionHall(parser));
            }
            else {
                skip(parser);
            }
        }
        return exhibitionHalls;
    }

    private ShowroomItem readExhibitionHall(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<DataItem> exhibits = new ArrayList<DataItem>();
        String name = null;
        String englishName = null;
        String floor = null;
        parser.require(XmlPullParser.START_TAG, namespace, "exhibitionHall");
        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tag = parser.getName();
            switch (tag) {
                case "name":
                    name = readText(parser, tag);
                    break;
                case "englishName":
                    englishName = readText(parser, tag);
                    break;
                case "floor":
                    floor = readText(parser, tag);
                    break;
                case "exhibit":
                    exhibits.add(readExhibit(parser));
                    exhibits.get(exhibits.size()-1).floor(Integer.parseInt(floor)).location(name);
                    break;
                default:
                    skip(parser);
                    break;
            }
        }
        ShowroomItem exhibitionHall = new ShowroomItem(name, englishName,Integer.parseInt(floor), exhibits);
        return exhibitionHall;
    }

    private DataItem readExhibit(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, namespace, "exhibit");
        String name = null;
        String imgId = null;
        String dynasty = null;
        String type = null;
        String author = null;
        String description = null;
        //TODO id++
        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String tag = parser.getName();
            switch (tag) {
                case "name" :
                    name = readText(parser, tag);
                    break;
                case "imgId" :
                    imgId = "R.drawable." + readText(parser, tag);
                    break;
                case "dynasty" :
                    dynasty = readText(parser, tag);
                    break;
                case "type" :
                    type = readText(parser, tag);
                    break;
                case "author" :
                    author = readText(parser, tag);
                    break;
                case "description" :
                    description = readText(parser, tag);
                    break;
                default:
                    break;
            }
        }
        return new DataItem(name, Integer.parseInt(imgId), description, dynasty, type, author);
    }

    private String readText(XmlPullParser parser, String tag) throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, namespace, tag);
        String context = getText(parser);
        parser.require(XmlPullParser.END_TAG, namespace, tag);
        return context;
    }

    private String getText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();//skip the end tag
        }
        return result;
    }

    private void skip(XmlPullParser parser)throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
