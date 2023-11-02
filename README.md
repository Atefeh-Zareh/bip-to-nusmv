This project provides an automated translation from the BIP language
to the NuSMV language in order to verify and validate some properties
in the BIP model.


# Using BIP-to-NuSMV

You can run the tool using the following command:

    java -jar input.bip output.smv [guide.txt]

**input.bip:** BIP model that you want to transform<br/>
**output.smv:** Name of the SMV file that will be generated<br/>
**guide.txt:** Optional arguement; explained below

You can find some example input, output, and guide files
[here](https://www.dropbox.com/sh/hslyx6zrbyk3d66/AACJKsGfGSUXFK_8hW0mFUuza?dl=0).

# Limitations

The tool covers a subset of BIP models. However, its limitations will
not be restrictive in most cases, as will be discussed below.

1. **The input.bip file must use the old BIP syntax.** The example
   models use the old syntax, and it is a straightforward syntax
   substitution to get an old BIP model from a new one. The tool will
   complain if the input file cannot be compiled.
2. **The input BIP model must be flat.** That is, it must contain only
   one compound type, which in turn contains all the atoms. Non-flat
   models can be transformed into flat models manually, relatively
   easily for small models.
3. **General PetriNets are not supported; only single-location
   places.** From a single place, you can have multiple outgoing and
   incoming transitions. The restriction is that transitions are from
   a single place to another.
4. **Trigger ports are not supported; all ports must be synchrons.**
   It is possible to modify connectors in such a way as to model a
   trigger port using only synchrons.
5. **Hierarchical connectors are not supported.** Connectors
   themselves should not export ports.
6. **Priorites are not supported.** This is not a real limitation, as
priorities can be handled. Adding them to the generated SMV file
manually is possible and relatively straightforward.  Message passing
of boolean and integer variables is supported. Other types of
variables should not exist in the model.
7. **Arithmetic operations are supported. Function calls are not
   supported.** These can be abstracted by replacing them with their
   return values.
8. **From a single port-place pair, only one transition is allowed.**
   This is not a real limitation as its use can be avoided. Moreover,
   BIP compilers would give warnings in such cases. The tool, however,
   will not give a warning, and might behave incorrectly.

If you require any clarification regarding these limitations, or
assistance in using the tool or in working around some of these
points, feel free to contact Wajeb Saab.

# The Guide File

The BIP-to-NuSMV tool takes as an optional third paramter a guide text
file.  This file is used to specify the bounds on integers used in the
BIP model.

NuSMV cannot represent integers of infinite domain. Therefore, an
integer is represented as a signed word of specified size in bits.

Integer variables used in BIP models often have a restricted domain as
well. The guide file can be used to specify the size of the integers
used in the BIP model, to allow the tool to use appropriate sizes in
the generated SMV file.

The format of the guide file is given with some examples of guide
files in the link above.

Although optional, it is recommended to use this file, since the tool
is configured to assign a default size of 3 bits for all integers
otherwise.

*Copyright (c) 2013–2016, RiSD Laboratory, EPFL, Switzerland. All
right reserved.*<br/>
*Author: Wajeb Saab*

# Disclaimer
This repository provides the project as an open-source resource for further use and development after the consent of the developers and owners, whose confirmations can be found in the 'docs' folder. Additional project details can be found on the [BIP-to-NuSMV transformation tool page](https://archiveweb.epfl.ch/risd.epfl.ch/bip2nusmv.html). This repository is meant to be used in compliance with all relevant legal and ethical guidelines.
