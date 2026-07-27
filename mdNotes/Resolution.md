# Resolution

Rules of inference are just specializations of resolution.
To write the inference rule as a resolution, convert all statements to disjunctions.

### Modus Ponens

$$
\begin{aligned}
p \to q &\\
p &\\
\hline
q &
\end{aligned}
$$

$$
\begin{aligned}
\neg p \lor q &\\
p \lor \bot &\\
\hline
q \lor \bot &
\end{aligned}
$$

### Modus Tollens

$$
\begin{aligned}
p \to q &\\
\neg q &\\
\hline
\neg p &
\end{aligned}
$$

$$
\begin{aligned}
\neg p \lor q &\\
\neg q \lor \bot&\\
\hline
\neg p \lor \bot&
\end{aligned}
$$

### Hypothetical Syllogism

$$
\begin{aligned}
p \to q &\\
q \to r &\\
\hline
p \to r &
\end{aligned}
$$

$$
\begin{aligned}
\neg p \lor q &\\
\neg q \lor r &\\
\hline
\neg p \lor r &
\end{aligned}
$$

### Disjunctive Syllogism

$$
\begin{aligned}
p \lor q &\\
\neg p &\\
\hline
q &
\end{aligned}
$$

$$
\begin{aligned}
p \lor q &\\
\neg p \lor \bot &\\
\hline
q \lor \bot &
\end{aligned}
$$
