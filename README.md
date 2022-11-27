# What is a proof-of-reserves audit

With cryptocurrency exchanges the saying goes: "Not your keys, not your coins". Proof of reserves provides an indication of whether customer funds are safe, meaning that sufficient funds are backing all deposits. If all clients decided to withdraw all their crypto holdings the exchange will have enough liquidity to process everyone's withdrawal requests.

# What is a proof-of-liabilities audit

Proof of reserves can give you an indication of the amount of assets under the exchanges' control. But if the exchange holds 10 bitcoin while clients deposited 20 bitcoin, then their liabilities exceed their assets. We therefore have to determine how much bitcoin is owed to users and compare the amount to the reserves in order to determine the health of the exchange. The best process to determine total liabilities, is to have a trusted, external auditor view all client balances. The auditor should then construct a Merkle Tree by including all of the client balances into the base layer (leaves) of the tree. By doing this, it enables every client of the exchange to independently and mathematically verify that his account balances were included in the audit.

# What is a hash

Calculating the hash of some input data prodoces a pseudorandom string of characters that are unique to that input data. If you change any character of the input data the resulting hash will be completely different.

For our application, we use SHA256 (Secure Hashing Algorithm). It takes input data of any length and produces a hash of length 256 bits which can be presented as 64 hexadecimal characters. The number of possible combinations in 256 bits are in the order of 10^77, which is a mere 1000 times less than the amount of atoms in the visible universe (10^80). To produce a colission in SHA256 (2 different inputs producing the same SHA256 output) you would need to conduct about 2^63 operations, which means that the algorithm should be secure for at least another 100 years.

We use a double SHA256 (SHA256d) hash to build the Merkle Trees. This is to add another layer of security.

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
