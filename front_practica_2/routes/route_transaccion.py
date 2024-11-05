from .route_familia import *

router_transaccion = Blueprint('router_transaccion',__name__)

@router_transaccion.route('/transaccion/table')
def table_transaccion():
    response = requests.post('http://localhost:8080/api/transaccion/all')
    transacciones = response.json()['data']
    i = 1
    for transaccion in transacciones:
        transaccion['numero'] = i
        headers = {'Content-Type':'application/json'}
        familia = requests.post('http://localhost:8080/api/familia/get',json={'id':transaccion['familiaId']},headers=headers).json()['data']
        transaccion['apellidosFamilia'] = familia['apellidosRepresentantes']
        generador = requests.post('http://localhost:8080/api/generador/get',json={'id':transaccion['generadorId']},headers=headers).json()['data']
        transaccion['generadorModelo'] = generador['modelo']
        i += 1
    return render_template('transaccion/table_transaccion.html',transacciones=transacciones)


@router_transaccion.route('/transaccion/save')
def save_transaccion():
    response = requests.post('http://localhost:8080/api/familia/all')
    familias = response.json()['data']
    response = requests.post('http://localhost:8080/api/generador/all')
    generadores = response.json()['data']
    context={
        'familias' : familias,
        'generadores' : generadores
    }
    return render_template('/transaccion/save_transaccion.html',context=context)
    
@router_transaccion.route('/transaccion/save/send',methods=['POST'])
def save_transaccion_send():
    data = request.form.to_dict()
    headers = {'Content-Type':'application/json'}
    response = requests.post('http://localhost:8080/api/transaccion/save/',json=data,headers=headers)
    if response.status_code == 200:
        flash('Datos de la transaccion guardados exitosamente!',category='info')
    else:
        flash('Ocurrió un error al intentar guardar los datos',category='error')
    return redirect('/transaccion/table')

@router_transaccion.route('/transaccion/update/<int:id>')
def update_transaccion(id):
    json={'id':id}
    headers={'Content-Type':'application/json'}
    response = requests.post('http://localhost:8080/api/transaccion/get/',json=json,headers=headers)
    transaccion = response.json()['data']
    response = requests.post('http://localhost:8080/api/generador/all')
    generadores = response.json()['data']
    response = requests.post('http://localhost:8080/api/familia/all')
    familias = response.json()['data']
    context = {'familias': familias, 'generadores': generadores}
    return render_template('transaccion/update_transaccion.html',transaccion=transaccion,context=context)

@router_transaccion.route('/transaccion/update/send',methods=['POST'])
def update_transaccion_send():
    json = request.form.to_dict()
    headers = {'Content-Type':'application/json'}
    response = requests.post('http://localhost:8080/api/transaccion/update',json=json,headers=headers)
    print(response)
    if response.status_code == 200:
        flash('Datos de la transaccion actualizados exitosamente!',category='info')
    else:
        flash('Ocurrió un error al intentar actualizar los datos',category='error')
    return redirect('/transaccion/table')


@router_transaccion.route('/transaccion/delete/<int:id>')
def delete_transaccion(id):
    json = {'id':id}
    headers={'Content-Type':'application/json'}
    response = requests.post('http://localhost:8080/api/transaccion/get',json=json,headers=headers)
    transaccion = response.json()['data']
    return render_template('transaccion/delete_transaccion.html',transaccion=transaccion)

@router_transaccion.route('/transaccion/delete/send',methods=['POST'])
def delete_transaccion_send():
    json = request.form.to_dict()
    headers = {'Content-Type':'application/json'}
    response = requests.post('http://localhost:8080/api/transaccion/delete',json=json,headers=headers)
    print(response)
    if response.status_code == 200:
        flash('Datos de la transaccion eliminados',category='info')
    else:
        flash('Ocurrió un error al intentar eliminar los datos',category='error')
    return redirect('/transaccion/table')

