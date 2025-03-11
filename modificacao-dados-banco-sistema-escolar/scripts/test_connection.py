import os
import pandas as pd
from sqlalchemy import create_engine, text
from dotenv import load_dotenv

load_dotenv()

def criar_engine():
    user = os.getenv('DB_USER')
    password = os.getenv('DB_PASSWORD')
    host = os.getenv('DB_HOST')
    port = os.getenv('DB_PORT')
    database = os.getenv('DB_NAME')
    
    url = f"postgresql://{user}:{password}@{host}:{port}/{database}"
    
    engine = create_engine(
        url,
        connect_args={'client_encoding': 'utf8'},
        echo=False  
    )
    
    return engine

def ler_tabela(nome_tabela):
    try:
        engine = criar_engine()
        
        query = f"SELECT * FROM {nome_tabela} LIMIT 10"
        df = pd.read_sql_query(query, engine)
        
        return df
    except Exception as e:
        print(f"Erro ao ler tabela: {e}")
        return None

if __name__ == "__main__":
    try:
        engine = criar_engine()
        
        with engine.connect() as conn:
            result = conn.execute(text("SELECT 1 as test"))
            row = result.fetchone()
            print(f"Conexão bem-sucedida! Resultado: {row.test}")
            
            result = conn.execute(text(
                "SELECT table_name FROM information_schema.tables WHERE table_schema='public'"
            ))
            tables = [row[0] for row in result]
            
            if tables:
                print("\nTabelas disponíveis:")
                for table in tables:
                    print(f"- {table}")
                
                if tables:
                    primeira_tabela = tables[0]
                    print(f"\nPrimeiras linhas da tabela '{primeira_tabela}':")
                    df = ler_tabela(primeira_tabela)
                    if df is not None and not df.empty:
                        print(df.head())
                    else:
                        print("Tabela vazia ou erro ao ler dados.")
            else:
                print("\nNenhuma tabela encontrada no esquema 'public'.")
                
    except Exception as e:
        print(f"Erro ao conectar: {e}")