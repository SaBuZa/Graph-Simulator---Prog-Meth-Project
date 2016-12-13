package logic;

import exception.DeserializeFailException;

public interface ISerializable {
	public String serialize();

	public void deserialize(String serializedData) throws DeserializeFailException;
}
