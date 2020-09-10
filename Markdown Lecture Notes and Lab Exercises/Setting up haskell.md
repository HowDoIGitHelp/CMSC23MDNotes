# Setting up Haskell

To start writing Haskell code, install Haskell through stack. Stack is found in the folder called "Haskell/Stack" inside the provided course pack. Copy the Stack folder and place it in your computer. To be able to use stack anywhere, add your copy of the stack folder in the PATH variable of your computer.

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

