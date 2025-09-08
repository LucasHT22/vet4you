# vet4you
Por Lucas Eduardo, Lucas Honda e Nathaly Pereira

Login cliente/admin/colab
Sign up
Dashboard - Noticias carrossel - icones/botoes - feedback
	manage news & feedbacks
	Comprovantes
	Histórico
	Teleconsulta
	Agendar cliente
	Balanço financeiro admin

## Model (Entidades do banco)
| Classe | Atributos Principais | Métodos Principais |
|--------|--------------------|------------------|
| Usuario | idUsuario, nome, telefone, email, username, password, isAdmin, cpf, isActive | getters/setters, validarLogin() |
| Endereco | idEndereco, logradouro, numero, complemento, bairro, cidade, estado, cep, usuario | getters/setters |
| Paciente | idPaciente, nome, raca, animal, idade, sexo, usuario, plano | getters/setters |
| Veterinario | idVeterinario, nome, especialidade, crmvet | getters/setters |
| Consulta | idConsulta, timestamp, motivo, diagnostico, paciente, veterinario | getters/setters |
| Exame | idExame, tipo, resultado, data, consulta | getters/setters |
| Produto | idProduto, nome, tipo, preco, estoque, consulta | getters/setters |
| Prescricao | idPrescricao, quantidade, instrucoes, consulta, veterinario, produto | getters/setters |
| Internacao | idInternacao, dataEntrada, dataSaida, observacoes, veterinario, paciente | getters/setters |
| Cirurgia | idCirurgia, tipo, data, observacoes, paciente, veterinario | getters/setters |
| Pagamento | idPagamento, servico, formaPagamento, valor, status, dataPagamento, paciente | getters/setters |
| Cartao | idCartao, plano, validade, titular | getters/setters |

---

## DAO (Acesso a Dados)
| Classe | Métodos Principais |
|--------|------------------|
| UsuarioDAO | inserir(Usuario), atualizar(Usuario), deletar(id), buscarPorId(id), listarTodos() |
| EnderecoDAO | inserir, atualizar, deletar, buscarPorId, listarTodos |
| PacienteDAO | inserir, atualizar, deletar, buscarPorId, listarTodos |
| VeterinarioDAO | inserir, atualizar, deletar, buscarPorId, listarTodos |
| ConsultaDAO | inserir, atualizar, deletar, buscarPorId, listarTodos |
| ExameDAO | inserir, atualizar, deletar, buscarPorId, listarTodos |
| ProdutoDAO | inserir, atualizar, deletar, buscarPorId, listarTodos |
| PrescricaoDAO | inserir, atualizar, deletar, buscarPorId, listarTodos |
| InternacaoDAO | inserir, atualizar, deletar, buscarPorId, listarTodos |
| CirurgiaDAO | inserir, atualizar, deletar, buscarPorId, listarTodos |
| PagamentoDAO | inserir, atualizar, deletar, buscarPorId, listarTodos |
| CartaoDAO | inserir, atualizar, deletar, buscarPorId, listarTodos |

---

## Controller (Regras de negócio)
| Classe | Métodos Principais |
|--------|------------------|
| UsuarioController | cadastrarUsuario(), removerUsuario(), listarUsuarios(), autenticar() |
| PacienteController | cadastrarPaciente(), listarPacientes() |
| VeterinarioController | cadastrarVeterinario(), listarVeterinarios() |
| ConsultaController | marcarConsulta(), listarConsultas() |
| ExameController | registrarExame(), listarExames() |
| ProdutoController | cadastrarProduto(), listarProdutos() |
| PrescricaoController | registrarPrescricao(), listarPrescricoes() |
| InternacaoController | registrarInternacao(), listarInternacoes() |
| CirurgiaController | registrarCirurgia(), listarCirurgias() |
| PagamentoController | registrarPagamento(), listarPagamentos(), processarPagamento() |
| CartaoController | cadastrarCartao(), listarCartoes() |
| AdminController | gerarBalancoFinanceiro(), gerarDashboardGeral(), listarTodosUsuarios(), relatoriosGerais() |
| UsuarioDashboardController | marcarConsulta(), listarConsultasUsuario(), pagarServico(), listarPagamentosUsuario() |

---

## View (Interfaces para usuário e admin)
| Classe | Métodos Principais |
|--------|------------------|
| LoginView | mostrarLogin(), autenticarUsuario() |
| DashboardAdminView | mostrarBalancoFinanceiro(), mostrarDashboardGeral(), relatoriosFinanceiros() |
| DashboardVeterinarioView | listarConsultas(), registrarConsulta() |
| DashboardUsuarioView | marcarConsulta(), pagarServico(), listarConsultas(), listarPagamentos() |
| CadastroUsuarioView | cadastrarUsuario(), listarUsuarios() |
| CadastroPacienteView | cadastrarPaciente(), listarPacientes() |
| CadastroVeterinarioView | cadastrarVeterinario(), listarVeterinarios() |
| CadastroConsultaView | registrarConsulta() |
| CadastroExameView | registrarExame() |
| CadastroProdutoView | cadastrarProduto() |
| CadastroPrescricaoView | registrarPrescricao() |
| CadastroInternacaoView | registrarInternacao() |
| CadastroCirurgiaView | registrarCirurgia() |
| CadastroPagamentoView | registrarPagamento() |
| CadastroCartaoView | cadastrarCartao() |

---

## Utils (Validações e helpers)
| Classe | Métodos Principais | Propósito |
|--------|------------------|-----------|
| CPFUtils | validarCPF(String cpf): boolean | Validar CPF do usuário ou paciente |
| EnderecoUtils | validarCEP(String cep): boolean, validarEstado(String estado): boolean | Validar dados de endereço como CEP e estado |
| DataUtils | formatarData(Date data): String, validarData(Date data): boolean | Formatar e validar datas |
| PagamentoUtils | validarValor(double valor): boolean, validarFormaPagamento(String forma): boolean | Validar informações de pagamento |
| ConsultaUtils | validarHorario(Date dataHora): boolean, verificarConflitoConsulta(Paciente p, Date dataHora): boolean | Validar horários de consulta e conflitos |
