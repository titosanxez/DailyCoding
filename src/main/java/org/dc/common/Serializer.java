package org.dc.common;

public interface Serializer<Input, Output> {

    Output serialize(Input input);

    Input deserialize(Output output);
}
