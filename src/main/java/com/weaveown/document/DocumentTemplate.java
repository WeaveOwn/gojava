package com.weaveown.document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangwei
 * @date 2022/5/5
 */
@Data
public class DocumentTemplate {
    private long id;
    private Element element;
    private String type;
    private String profile;
    private boolean isCustomRule;
    private String source;
    private String name;

    static class ApplicationTemplate {
        private long id;
        private DocumentTemplate template;
    }


    @Data
    static class Field {
        private long id;
        private String key;
        private String title;
        private String type;
        private Map<String, Field> properties;
    }


    @Data
    static class Rule {
        private long id;
        private boolean required;
        private Map<String, Object> items;

    }

    @Data
    static class Element {
        private long id;
        private String key;
        private String title;
        private String type;
        private boolean required;
        private List<Rule> rules;
        private Map<String, ? super Element> properties;
        private Element items;

    }


    public static void main(String[] args) throws JsonProcessingException {
        final Field field = new Field();
        field.setId(1L);
        field.setKey("companyName");
        field.setTitle("公司名称");
        field.setType("string");
        final Element element = new Element();
        element.setId(1L);
        element.setKey("companyName");
        element.setTitle("公司名称");
        element.setType("object");
        element.setRequired(true);
        final Element childen = getStringElement();
        Map<String, ? super Element> properties = new HashMap<>();
        properties.put(childen.getKey(), childen);
        element.setProperties(properties);
        ObjectMapper objectMapper = new ObjectMapper();
        final String s = objectMapper.writeValueAsString(element);
        System.out.println(s);
    }

    public static Element getStringElement() {
        final Element element = new Element();
        element.setId(1L);
        element.setKey("companyName");
        element.setTitle("公司名称");
        element.setType("string");
        Rule rule = new Rule();
        rule.setId(2L);
        rule.setRequired(true);
        rule.setItems(Maps.newHashMap());
        element.setRequired(true);
        element.setRules(Lists.newArrayList(rule));
        return element;
    }


}
