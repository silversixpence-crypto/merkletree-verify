import hashlib

# to calculate your leaf hash, paste the following information here:
# with example data expect: 253FB2FE65EC3A4DC0F095F1A3D9306EE64558CF1128455989250F52FA6A5504
userid = "287e29b8-de5b-4924-8906-b216f2d48cd6"
salt = "2A496ECE"
balances = "BTC=76.83|ETH=19.26|XRP=68.95|USDC=6.32"

to_be_hashed = userid + salt + balances

print("Data to be hashed: {}".format(to_be_hashed))

first_hash = hashlib.sha256(to_be_hashed.encode('utf-8')).digest()
final_hash = hashlib.sha256(first_hash).hexdigest().upper()

print("User ID: {}".format(userid))
print("Salt: {}".format(salt))
print("Balances: {}".format(balances))

print("Merkle Leaf: {}".format(final_hash))

# to calculate the next node hash, paste the four node hashes here:
# with example data expect: C0A8940CBF1F7E924B24D8612E0F2E3E2FE77679B09CEB7BADF8FF49FB39AECD
node1 = "B09DC86278DE3AB2E78D4978B88282DAD07AFEF25A6354435444557D0A2F7D06"
node2 = "B1925680ACC7728A8B7AD83936C1124537635F93164A0E990F005B5CB5E31535"
node3 = "8B55D98DDA16E22E66AFD4E7DB6390BE3A1EF9CE8EB8832CE53EB023396FFD37"
node4 = "E53D261F0B974D9DA5C599538CFB329742E9413F23D4999A78F73A00F843E630"

to_be_hashed = node1 + node2 + node3 + node4

print("Data to be hashed: {}".format(to_be_hashed))

first_hash = hashlib.sha256(to_be_hashed.encode('utf-8')).digest()
final_hash = hashlib.sha256(first_hash).hexdigest().upper()

print("Node Hash: {}".format(final_hash))
