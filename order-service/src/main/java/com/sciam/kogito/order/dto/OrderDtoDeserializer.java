package com.sciam.kogito.order.dto;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.google.gson.Gson;

import java.io.IOException;

public class OrderDtoDeserializer extends StdDeserializer {


    public OrderDtoDeserializer() {
        this(null);
    }

    public OrderDtoDeserializer(Class vc) {
        super(vc);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        TreeNode node = jsonParser.getCodec().readTree(jsonParser);
        String value = node.toString();
        if (node instanceof TextNode) {
            value = ((TextNode) node).textValue();
        }

        Gson gson = new Gson();
        return gson.fromJson(value, OrderDto.class);

    }
}
