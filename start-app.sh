#!/bin/sh

# Aguardando o banco H2 estar pronto na porta 1521
echo "Aguardando H2 Database..."
until nc -z -v -w30 h2database 1521
do
  echo "Aguardando H2 estar ouvindo na porta 1521..."
  sleep 10
done

# Quando o H2 estiver ouvindo e o SQL Server estiver pronto, inicia a aplicação
echo "H2 e SQL Server estão ouvindo nas portas 1521 e 1433. Iniciando aplicação..."
exec java -jar /app/gerenciadorproduto-1.0.jar
