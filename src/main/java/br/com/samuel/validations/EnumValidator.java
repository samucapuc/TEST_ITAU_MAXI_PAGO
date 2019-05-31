package br.com.samuel.validations;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidateEnum, String> {
	private Set<String> allowedValues;
	private boolean required;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void initialize(ValidateEnum targetEnum) {
		Class<? extends Enum> enumSelected = targetEnum.targetClassType();
		required = targetEnum.required();
		allowedValues = (Set<String>) EnumSet.allOf(enumSelected).stream()
				.map(e -> ((Enum<? extends Enum<?>>) e).name()).collect(Collectors.toSet());
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return ((required && value != null) || !required && value == null) && allowedValues.contains(value) ? true
				: false;
	}
}