package com.epam.protocol.builder;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import com.epam.protocol.domain.message.Message;

public abstract class MessageBuilder<T extends Message> {
	public abstract T buildMessage(ByteBuffer byteBuffer);

	public String getString(ByteBuffer byteBuffer) {
		String result = "";
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();
		try {
			result = decoder.decode(byteBuffer).toString();
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
