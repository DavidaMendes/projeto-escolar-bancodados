import sys

print(f"Codificação padrão do sistema: {sys.getdefaultencoding()}")
print(f"Codificação do terminal: {sys.stdout.encoding}")

usuario = "user"
senha = "senha"
host = "localhost"
porta = "5432"
banco = "banco"

print("\nVerificando componentes individualmente:")
for nome, valor in [
    ("Usuário", usuario),
    ("Senha", senha),
    ("Host", host),
    ("Porta", porta),
    ("Banco", banco)
]:
    try:
       
        encoded = valor.encode('utf-8')
        decoded = encoded.decode('utf-8')
        print(f"{nome}: OK")
    except Exception as e:
        print(f"{nome}: ERRO - {e}")
        
        print(f"  Bytes: {[b for b in valor.encode('latin-1')]}")