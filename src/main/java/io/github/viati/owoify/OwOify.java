package io.github.viati.owoify;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class OwOify implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("owoify");
	public static Map<String, String> owoMap = new HashMap<>(Map.of("r", "w", "l", "w","R", "W","L", "W","no", "nu","has", "haz","have", "haz","you", "uu","the ", "da ", "The ", "Da "));

	@Override
	public void onInitialize() {
		owoMap.put("Qu", "Qwu");
		LOGGER.info("OwOing your minecraft!");
	}

	public static String owoify(String str) {
		var ref = new Object() {
			String ph = str;
		};
		owoMap.forEach((s, s2) -> {
			if (ref.ph.contains(s)) ref.ph = ref.ph.replaceAll(s, s2);
		});
		return ref.ph;
	}
}
