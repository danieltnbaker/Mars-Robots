# Mars-Robots

Simple kotlin file, compile and run. I use IntelliJ but anything should work fine.

Commands are entered line by line and not in one dump, for example, press enter after each one of these lines:
> 55 \
> 12E \
> FFRFLFFRF

## Things to consider for v2:

- Empty lines break the app as there isn't a check for them, I would possible add it to the initial while loop
- Incorrect position inputs will break the app, i.e. S12. I would add parsers for max coordinates and positions
- I didn't have time to implement scents, but if I were to I would:
    - Create an array of 'scents' and add positions every time a robot is lost
    - Check that array every time a forward move breaks the boundaries of Mars
