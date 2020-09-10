# Setting up Haskell

## Installation

### Windows

To start writing Haskell code, install Haskell through stack. A stack zip can be found in the windows folder. Copy the Stack folder and place it in your computer. To be able to use stack anywhere, add your copy of the stack folder in the PATH variable of your computer.

### OSX

For common Unix operating systems (including macOS), all you need to do is run. This will download and install it for you.

```
curl -sSL https://get.haskellstack.org/ | sh
```

or:

```
wget -qO- https://get.haskellstack.org/ | sh
```

Note that this script will ask for root access using `sudo` in order to use your platform's package manager to install dependencies and to install to `/usr/local/bin`.  If you prefer more control, follow the manual installation instructions for your platform below.

Binaries and tar files are also available in the OSX folder if you want to build it or extract it on your own. If you do that extract the archive and place `stack` somewhere on your `$PATH`.

## Linux

Run:

```
curl -sSL https://get.haskellstack.org/ | sh
```

or:

```
wget -qO- https://get.haskellstack.org/ | sh
```

Binaries and tar files are also available in the Linux folder if you want to build it or extract it on your own. If you do that extract the archive and place `stack` somewhere on your `$PATH`. Ensure you have required system dependencies installed.  These  include GCC, GNU make, xz, perl, libgmp, libffi, and zlib.  

## Getting Started

Once stack has been set-up using the steps above, you can run the GHC repl using the command 

```
> stack ghci
```

The first time you run this code, stack will automatically install the GHC compiler. 

After downloading GHC, you will be taken to the Prelude part of the your GHC repl. To test if everything is working properly, try the following Haskell expression:

```haskell
Prelude> show (1 + 3)
```

If everything is good to go, the GHC expression will evaluate to:

```haskell
"4"
```

To exit GHC run the following GHC command

```
:quit
```

