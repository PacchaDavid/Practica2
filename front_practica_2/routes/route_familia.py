from flask import Blueprint, redirect, render_template, request, flash
import requests

router_familia = Blueprint('router_familia',__name__)

@router_familia.route('/')
def home():
    return render_template('index.html')

@router_familia.route('/familia/table')
def table_familia():
    response = requests.post('http://localhost:8080/api/familia/all')
    familias = response.json()['data']
    numbers = range(0,familias.__len__())
    return render_template('familia/table_familia.html',familia=familias,numbers=numbers)


@router_familia.route('/familia/save')
def save_familia():
    response = requests.get('http://localhost:8080/api/familia/cantones_and_nivelesSE')
    return render_template('familia/save_familia.html',enumeracion=response.json())

@router_familia.route('/familia/save/send', methods=['POST'])
def save_familia_send():
    headers = {'Content-Type':'application/json'}
    data = request.form.to_dict()
    print(data)
    response = requests.post('http://localhost:8080/api/familia/save',json=data,headers=headers)
    if response.status_code == 200:
        flash('Datos de la familia guardados exitosamente!',category='info')
        return redirect('/familia/table')
    else:
        flash('Algo ha salido mal al intentar guardar los datos',category='error')
        return redirect('/familia/table')
    


@router_familia.route('/familia/update/<int:id>')
def update_familia(id):
    headers = {"Content-Type":"application/json"}
    response = requests.post(f'http://localhost:8080/api/familia/get',json={"id":id},headers=headers)
    familia = response.json()['data']
    response = requests.get('http://localhost:8080/api/familia/cantones_and_nivelesSE')
    enums = response.json()
    return render_template('familia/update_familia.html',context={"familia": familia, "enums" : enums})

@router_familia.route('/familia/update/send',methods=['POST'])
def update_familia_send():
    data = request.form.to_dict()
    headers = {'Content-Type':'application/json'}
    response = requests.post('http://localhost:8080/api/familia/update',json=data,headers=headers)
    if response.status_code == 200:
        flash('Datos de Familia modificados con éxito',category='info')
        return redirect('/familia/table')
    else:
        flash('Ocurrió un error al intentar modificar los datos',category='error')
        return redirect('/familia/table')

@router_familia.route('/delete/familia/<int:id>')
def delete_familia(id):
    response = requests.post(f'http://localhost:8080/api/familia/get',json={'id':id})
    familia = response.json()['data']
    return render_template('familia/delete_familia.html',familia=familia)

@router_familia.route('/delete/familia/send',methods=['POST'])
def delete_familia_send():
    headers = {'Content-Type':'application/json'}
    json = request.form.to_dict()
    print(json)
    response = requests.post('http://localhost:8080/api/familia/delete',json=json,headers=headers)
    print(response)
    if response.status_code == 200:
        flash('Familia eliminada!',category='info')
        return redirect('/familia/table')
    else:
        flash('Algo sallió mal, no se eliminaron los datos',category='error')
        return redirect('/familia/table')