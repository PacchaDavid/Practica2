from .route_familia import *

router_generador = Blueprint('router_generador',__name__)

@router_generador.route('/generador/table')
def table_generador():
    response = requests.post('http://localhost:8080/api/generador/all')
    generadores = response.json()['data']
    return render_template('generador/table_generador.html',list=generadores)


@router_generador.route('/generador/save')
def save_generador():
    return render_template('/generador/save_generador.html')
    
@router_generador.route('/generador/save/send',methods=['POST'])
def save_generador_send():
    data = request.form.to_dict()
    headers = {'Content-Type':'application/json'}
    response = requests.post('http://localhost:8080/api/generador/save/',json=data,headers=headers)
    if response.status_code == 200:
        flash('Datos del generador guardados exitosamente!',category='info')
    else:
        flash('Ocurrió un error al intentar guardar los datos',category='error')
    return redirect('/generador/table')

@router_generador.route('/generador/update/<int:id>')
def update_generador(id):
    json={'id':id}
    headers={'Content-Type':'application/json'}
    response = requests.post('http://localhost:8080/api/generador/get/',json=json,headers=headers)
    generador = response.json()['data']
    return render_template('generador/update_generador.html',generador=generador)

@router_generador.route('/generador/update/send',methods=['POST'])
def update_generador_send():
    json = request.form.to_dict()
    headers = {'Content-Type':'application/json'}
    response = requests.post('http://localhost:8080/api/generador/update',json=json,headers=headers)
    print(response)
    if response.status_code == 200:
        flash('Datos del generador actualizados exitosamente!',category='info')
    else:
        flash('Ocurrió un error al intentar actualizar los datos',category='error')
    return redirect('/generador/table')


@router_generador.route('/generador/delete/<int:id>')
def delete_generador(id):
    json = {'id':id}
    headers={'Content-Type':'application/json'}
    response = requests.post('http://localhost:8080/api/generador/get',json=json,headers=headers)
    generador = response.json()['data']
    return render_template('generador/delete_generador.html',generador=generador)

@router_generador.route('/generador/delete/send',methods=['POST'])
def delete_generador_send():
    json = request.form.to_dict()
    headers = {'Content-Type':'application/json'}
    response = requests.post('http://localhost:8080/api/generador/delete',json=json,headers=headers)
    print(response)
    if response.status_code == 200:
        flash('Datos del generador eliminados',category='info')
    else:
        flash('Ocurrió un error al intentar eliminar los datos',category='error')
    return redirect('/generador/table')

