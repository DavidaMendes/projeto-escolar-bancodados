import os
import pandas as pd
import psycopg2
from sqlalchemy import create_engine
from dotenv import load_dotenv
from createcsv import CreateArquivo as ccsv

load_dotenv()

db_config = {
    'user': os.getenv('DB_USER'),
    'password': os.getenv('DB_PASSWORD'),
    'host': os.getenv('DB_HOST'),
    'port': os.getenv('DB_PORT'),
    'database': os.getenv('DB_NAME')
}

conn_string = f"postgresql://{db_config['user']}:{db_config['password']}@{db_config['host']}:{db_config['port']}/{db_config['database']}"

def ler_dados_sqlalchemy():
    
    engine = create_engine(conn_string)
    
    df = pd.read_sql("SELECT * FROM aluno", engine)
    
    return df

def salvar_no_banco(df):
    # So utilizar quando necessário
    engine = create_engine(conn_string)
    df.to_sql('tabela_destino', engine, if_exists='replace', index=False)

def criador_arquivo(df):
    if df is not None:
        ccsv.gerar_csv(df)
        print("Arquivo CSV gerado com sucesso!")
    else:
        print("Erro: DataFrame vazio ou nulo.")

df = ler_dados_sqlalchemy()
df = df[['aluno_id', 'nome', 'sobrenome', 'ultimo_nome', 'cpf', 'telefone', 'email', 'idade']]
print(df.sort_index())

criador_arquivo(df)
