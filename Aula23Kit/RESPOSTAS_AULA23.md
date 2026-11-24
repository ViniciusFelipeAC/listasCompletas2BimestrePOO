# Aula 23 - Respostas

Teste na TelaCaoticaLegada (ALPHA, SEGREDO, 3): `[ALPHA] #521 -> VHJUHGR`

**Por que o botão é uma bomba-relógio?**

Porque tudo fica dentro do actionPerformed do botão: leitura dos campos, validação, conversão da chave, criptografia, checksum e até a lista que guarda o histórico. Isso quebra o SRP e o MVC. Não dá pra testar a regra sem abrir a tela, não dá pra reaproveitar o código em outro lugar e qualquer mudança na tela pode quebrar a regra de negócio.

Depois de separar em Model e Controller os 8 testes do CriptoMensagemTest passaram.
