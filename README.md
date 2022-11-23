# What is a proof-of-reserves audit

With cryptocurrency exchanges the saying goes: "Not your keys, not your coins". Proof of reserves shows whether customer funds are safe, meaning that sufficient funds are backing all deposits. If all clients decided to withdraw all their crypto holdings the exchange will enough to process everyone's withdrawal requests.

# What is a hash

Calculating the hash of some input data prodoces a seemingly random string of characters that are unique to that input data. If you change any character of the input data the resulting hash will be completely different.

For our application the most notable hashin gfunction is SHA256 (Secure Hashing Algorithm). It takes input data of any length and produces a hash of length 256 bits which can be presented as 64 hexadecimal characters. The number of combinations between 256 bits are in the order of 10^77; the number of atoms in the visible universe is 10^80, only 1000 times bigger.

We use a double SHA256 (SHA256d) hash for increased security against... 

# What is a merkle tree

In cryptography and computer science, a hash tree or Merkle tree is a tree in which every "leaf" (node) is labelled with the cryptographic hash of a data block, and every node that is not a leaf (called a branch, inner node, or inode) is labelled with the cryptographic hash of the labels of its child nodes. A hash tree allows efficient and secure verification of the contents of a large data structure. https://en.wikipedia.org/wiki/Merkle_tree

Our merkle trees has a width of four for improves user experience...

# Why do we generate a merkle tree as part of a proof-of-reserver audit

To prove to a user that his/her exchange balance is included in the audit...

# How do we generate the merkle tree

## Leaf

We use your data + SHA256d to generate a leaf that is unique to you. The leaves are level-0 of the merkle tree.

## Nodes

The first level of nodes we take four leaves, concatenate them and take the SHA256d of the resulting string. This will be a node on level-1. After all the leaves are hashed together to produce the level-1 nodes, we hash the level-1 nodes together four-by-four to produce the level-2 nodes. And so on until only a single root node is left.

## Balances or not?

Security vs show in webpage

## Uniqueness

Use salt or audit id