import os  
import pandas as pd

class CreateArquivo:
    contador = 0

    @classmethod
    def gerar_csv(cls, df):
        cls.contador += 1
        os.makedirs('arquivos-gerados/csv', exist_ok=True)  
        df.to_csv(f'arquivos-gerados/csv/arquivoDataBase{cls.contador}.csv',index=False)