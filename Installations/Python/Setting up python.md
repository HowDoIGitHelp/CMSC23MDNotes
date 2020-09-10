# Setting up python

## Installation

### Windows

To install python just run the installer from the Windows folder (choose amd64 for machines with AMD architectures). Make sure python is added in your PATH folder

### OSX

To install python just run the installer from the OSX folder. Make sure python is added in your PATH folder

### Linux

Your distro might have come with python installed. To check this, run the command

```
$ python3 --version
```

If the command is not recognized install Ubuntu using the following steps

If you are using Ubuntu 16.10 or newer, then you can easily install Python 3.6 with the following commands:

```
$ sudo apt-get update
$ sudo apt-get install python3.6
```

If you’re using another version of Ubuntu (e.g. the latest LTS  release) or you want to use a more current Python, we recommend using  the [deadsnakes PPA](https://launchpad.net/~deadsnakes/+archive/ubuntu/ppa) to install Python 3.8:

```
$ sudo apt-get install software-properties-common
$ sudo add-apt-repository ppa:deadsnakes/ppa
$ sudo apt-get update
$ sudo apt-get install python3.8
```

If you are using other Linux distribution, chances are you already have Python 3 pre-installed as well. If not, use your distribution’s package manager. For example on Fedora, you would use dnf:

```
$ sudo dnf install python3
```

Note that if the version of the `python3` package is not recent enough for you, there may be ways of installing more recent versions as well, depending on you distribution. For example installing the `python36` package on Fedora 25 to get Python 3.6. 

If you want to build python from source, it is available in the Linux folder.

## Getting Started

To start python repl on the command line, use the `python` command. Make sure you have the path to python saved in your OS's `PATH` environment variable. Some python distributions have to be started with the specific version specified. For these distributions use the command `python2` or `python3`.

```
> python
Python 3.8.5 (tags/v3.8.5:580fbb0, Jul 20 2020, 15:43:08) [MSC v.1926 32 bit (Intel)] on win32
Type "help", "copyright", "credits" or "license" for more information.
>>>
```

To exit python shell, type CTRL-Z then enter

To run a python script (`*.py`), use the same `python` command followed by the path to the script

```
> python script.py
```

## 