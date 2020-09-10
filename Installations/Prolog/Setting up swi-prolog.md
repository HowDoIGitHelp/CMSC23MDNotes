# Setting up swi-prolog

## Installation

### Windows

Install using the installer found in the windows folder. Make sure it is added to your PATH variable so you can use it anywhere.

### OSX

Install using the installer found in the OSX folder. Make sure it is added to your PATH variable so you can use it anywhere.

### Linux (Ubuntu)

You can install it using a Ubuntu Personal Package Archive with these commands:

```
sudo apt-get install software-properties-common
```

```
sudo apt-add-repository ppa:swi-prolog/stable
sudo apt-get update
sudo apt-get install swi-prolog
```

### Linux supporting snap

```
sudo snap install swi-prolog
```

### Building swi-prolog

Here's info on how to build it from the tar ball in the Linux folder. https://www.swi-prolog.org/build/unix.html

## Getting Started

Once prolog is has been set-up you can run the `swipl` command to start swi-prolog

```
> swipl
```

to exit, use the `halt.` command (swipl will wait for a `.` for every query/command)

```prolog
?- halt.
```

To load prolog knowledge bases (`*.pl`), use the `swipl` command again but this time include the path to the prolog file as an argument.

```
> swipl knowledegebase.pl
```

## 