const bookIds = [1, 2, 3, 4, 5]; //Podemos aumentar o numero de IDs para fazer o loop
const token = pm.collectionVariables.get('token'); //Adicionar nas collections o $token

bookIds.forEach((id) => {
    const start = Date.now();

    pm.sendRequest({
        url: `http://localhost:8080/api/books/${id}/reserve`,
        method: 'POST',
        header: {
            'Authorization': `Bearer ${token}`
        }
    }, function (err, res) {
        const duration = Date.now() - start;
        console.log(`Book ${id} reserved in ${duration} ms - Status: ${res.code}`);
    });
});

