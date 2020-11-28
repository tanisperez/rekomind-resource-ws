package com.rekomind.resource.property;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

/**
 * Componente que captura el evento de finalización de carga del contexto de
 * Spring e imprime todas las propiedades y variables de las que es consciente
 * el contexto de Spring.
 *
 * @author Estanislao Pérez Nartallo
 */
@Component
public class PropertyLogger {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyLogger.class);

	private static final String LINE_SEPARATOR = "\\n";

	@EventListener
	@SuppressWarnings("rawtypes")
	public void handleContextRefresh(final ContextRefreshedEvent event) {
		final ApplicationContext context = event.getApplicationContext();
		final AbstractEnvironment env = (AbstractEnvironment) context.getEnvironment();

		LOGGER.info("============================== Environment and configuration ==============================");
		LOGGER.info("\tSpring active profiles = {}", Arrays.toString(env.getActiveProfiles()));

		final MutablePropertySources sources = env.getPropertySources();
		StreamSupport.stream(sources.spliterator(), false)
			.filter(ps -> ps instanceof EnumerablePropertySource)
			.map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
			.flatMap(Arrays::stream)
			.distinct()
			.forEach(prop -> logProperty(prop, env.getProperty(prop)));

		LOGGER.info("===========================================================================================");
	}

	/**
	 * Método que loguea la clave y el valor de una property.
	 *
	 *
	 * @param key
	 * @param value
	 */
	private static void logProperty(final String key, final String value) {
		String cleanValue = value;
		if (isPassword(key)) {
			cleanValue = hidePassword(value);
		} else if (containsLineSeparator(value)) {
			cleanValue = cleanLineSeparator(value);
		}
		LOGGER.info("\t{} = \"{}\"", key, cleanValue);
	}

	/**
	 * Determina si la clave de una property contiene unas credenciales o
	 * contraseña.
	 *
	 * @param key Un {@code String} que contiene la clave de una property.
	 * @return Devuelve true si la clave se llama {@code credentials} o
	 *         {@code password}, false en caso contrario.
	 */
	private static boolean isPassword(final String key) {
		return StringUtils.containsIgnoreCase(key, "credentials") || StringUtils.containsIgnoreCase(key, "password");
	}

	/**
	 * Devuelve un nuevo {@code String} que contiene un {@code *} por cada caracter
	 * del valor de la property.
	 *
	 * @param value Un {@code String} que contiene el valor de la property.
	 * @return Devuelve un {@code String} del mismo tamaño que el valor de la
	 *         propiedad, pero formado por {@code *}.
	 */
	private static String hidePassword(final String value) {
		return "*".repeat(value.length());
	}

	/**
	 * Determina si el valor de una property contiene saltos de línea.
	 *
	 * @param value Un {@code String} que contiene el valor de una property.
	 * @return Devuelve true si el valor de la property contiene saltos de línea,
	 *         false en caso contrario.
	 */
	private static boolean containsLineSeparator(final String value) {
		return value.contains("\n");
	}

	/**
	 * Reemplaza todos los saltos de línea del valor de una property por
	 * {@code PropertyLogger#LINE_SEPARATOR}.
	 *
	 * @param value Un {@code String} que contiene el valor de una property.
	 * @return Devuelve un {@code String} que contiene el valor de la property con
	 *         los saltos de línea reemplazados.
	 */
	private static String cleanLineSeparator(final String value) {
		return value.replace("\n", LINE_SEPARATOR);
	}

}
