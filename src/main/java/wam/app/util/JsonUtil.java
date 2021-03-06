package wam.app.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	/**
	 * json을 Map으로 변환
	 * @param json
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static HashMap<String, Object> convertJsonToObject(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<HashMap<String, Object>> typeReference = new TypeReference<HashMap<String, Object>>() {
		};
		HashMap<String, Object> object = objectMapper.readValue(json, typeReference);
		return object;
	}

	/**
	 * json을 List<Map<>>으로 변환
	 * @param json
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static List<HashMap<String, Object>> convertJsonToList(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		TypeReference<List<HashMap<String, Object>>> typeReference = new TypeReference<List<HashMap<String, Object>>>() {
		};
		List<HashMap<String, Object>> list = objectMapper.readValue(json, typeReference);
		return list;
	}

}
