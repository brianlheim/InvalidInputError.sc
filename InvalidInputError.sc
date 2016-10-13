// written by Brian Heim
// Thursday October 13, 2016

// an extension to MethodError that alerts the user to bad input
InvalidInputError : MethodError {
	var <>method, <>class, <>argName, <>spec;

	*new { arg receiver, argName, method, class, spec = nil;
		^super.new(nil, receiver).method_(method).class_(class).argName_(argName).spec_(spec);
	}

	errorString {
		var string = "Error: '%' is invalid input for %-%(%).".format(receiver, class.name, method.name, argName);
		if(spec.notNil) {string = string ++ " Expected input: %".format(spec)};
		^string;
	}
}

+Object {
	invalidInput {
		arg method, argName, value, spec;
		InvalidInputError(value, argName, method, this.class, spec).throw;
	}
}